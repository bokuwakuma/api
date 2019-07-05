package jp.alhinc.orchestra.domain.service.member;

import jp.alhinc.orchestra.domain.model.Member;
import jp.alhinc.orchestra.domain.model.MemberCredential;
import jp.alhinc.orchestra.domain.repository.member.MemberRepository;
import org.apache.ibatis.session.RowBounds;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;

//    @Autowired
//    JodaTimeDateFactory dateFactory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Member> searchMembers(String name, Pageable pageable) {
        List<Member> members;
        long total = memberRepository.countByContainsName(name);
        if (0 < total) {
            RowBounds rowBounds = new RowBounds((int) pageable.getOffset(), pageable.getPageSize());
            members = memberRepository.findPageByContainsName(name, rowBounds);
        } else {
            members = new ArrayList<>();
        }
        return new PageImpl<>(members, pageable, total);
    }

    @Override
    @Transactional(readOnly = true)
    public Member findOne(String memberId) {
        Member member = memberRepository.findOne(memberId);
        if (member == null) {
            // TODO Exception type
            throw new RuntimeException();
        }
        return member;
    }

    @Override
    public Member create(Member creatingMember) {

        MemberCredential creatingCredential = creatingMember.getCredential();

        Date currentDate = new Date();

        creatingMember.setCreatedAt(currentDate);
        creatingMember.setLastModifiedAt(currentDate);

        String signId = creatingCredential.getSignId();
        if (!StringUtils.hasLength(signId)) {
            signId = creatingMember.getEmailAddress();
            creatingCredential.setSignId(signId.toLowerCase());
        }

        String rawPassword = creatingCredential.getPassword();
        creatingCredential.setPassword(passwordEncoder.encode(rawPassword));
        creatingCredential.setPasswordLastChangeAt(currentDate);
        creatingCredential.setLastModifiedAt(currentDate);

        try {
            memberRepository.createMember(creatingMember);
            memberRepository.createCredential(creatingMember);
            return creatingMember;
        } catch (DuplicateKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Member update(String memberId, Member updatingMember) {
        Member member = findOne(memberId);
        // Member情報を上書きする
        modelMapper.map(updatingMember, member, "member.update");
        Date currentDate = new Date();
        member.setLastModifiedAt(currentDate);

        boolean updated = memberRepository.updateMember(member);
        if (!updated) {
            throw new ObjectOptimisticLockingFailureException(Member.class, member.getMemberId());
        }
        return member;
    }

    @Override
    public void delete(String memberId) {
        memberRepository.deleteCredential(memberId);
        memberRepository.deleteMember(memberId);
    }
}
