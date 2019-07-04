package jp.alhinc.orchestra.domain.repository.member;

import jp.alhinc.orchestra.domain.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface MemberRepository {

    Member findOne(String memberId);

    List<Member> findAll();

    long countByContainsName(String name);
    List<Member> findPageByContainsName(String name, RowBounds rowRounds);

    void createMember(Member creatingMember);
    void createCredential(Member creatingMember);

    boolean updateMember(Member updatingMember);

    void deleteMember(String memberId);
    void deleteCredential(String memberId);
}
