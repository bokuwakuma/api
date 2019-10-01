package jp.alhinc.orchestra.domain.service.member;

import jp.alhinc.orchestra.domain.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    List<Member> findAll();
    Page<Member> searchMembers(String name, Pageable pageable);
    Member findOne(String memberId);
    Member create(Member creatingMember);
    Member update(String memberId, Member updatingMember);
    void delete(String memberId);
}
