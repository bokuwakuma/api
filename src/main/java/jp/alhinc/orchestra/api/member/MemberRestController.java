package jp.alhinc.orchestra.api.member;

import jp.alhinc.orchestra.api.member.MemberResource.PostMembers;
import jp.alhinc.orchestra.api.member.MemberResource.PutMember;
import jp.alhinc.orchestra.domain.model.Member;
import jp.alhinc.orchestra.domain.service.member.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("members")
@RestController
public class MemberRestController {

    @Autowired
    MemberService memberService;

    @Autowired
    ModelMapper modelMapper;

    /**
     * 名前検索
     *
     * @param query
     * @param pageable
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<MemberResource> getMembers(@Validated MemberSearchQuery query,
                                           Pageable pageable) {
        Page<Member> page = memberService.searchMembers(query.getName(), pageable);

        List<MemberResource> memberResources = new ArrayList<>();
        for (Member member : page.getContent()) {
            memberResources.add(modelMapper.map(member, MemberResource.class));
        }
        Page<MemberResource> responseResource = new PageImpl<>(memberResources, pageable, page.getTotalElements());
        return responseResource;
    }

    /**
     * 一覧検索
     *
     * @return
     */
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<MemberResource> getMembers() {
//        List<Member> members = memberService.findAll();
//        List<MemberResource> memberResources = new ArrayList<>();
//        for (Member member : members) {
//            memberResources.add(beanMapper.map(member, MemberResource.class));
//        }
//        return memberResources;
//    }

    /**
     * ページ検索
     */
//    @GetMapping(params = {"page", "size"})
//    public List<MemberResource> findPaginated(@RequestParam int page,
//                                              @RequestParam int size,
//                                              UriComponentsBuilder uriBuilder,
//                                              HttpServletResponse response) {
//        Page<Member> resultPage = memberService.findPaginated(page, size);
//        if (page > resultPage.getTotalPages()) {
//            throw new RuntimeException();
//        }
//
//        // リソース変換
////        for (Member member : resultPage) {
////            resultPage = resultPage.map(member, MemberResource.class);
////        }
////        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<Foo>(
////                Foo.class, uriBuilder, response, page, resultPage.getTotalPages(), size));
//        return resultPage.getContent();
//
//    }


    /**
     * ID検索
     *
     * @param memberId
     * @return
     */
    @GetMapping("{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResource getMember(@PathVariable("memberId") String memberId) {
        Member member = memberService.findOne(memberId);
        MemberResource responseResource = modelMapper.map(member, MemberResource.class);
        return responseResource;
    }

    /**
     * メンバー作成
     * @param requestedResource
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResource postMembers(@RequestBody @Validated({PostMembers.class, Default.class})
                                              MemberResource requestedResource) {
        Member creatingMember = modelMapper.map(requestedResource, Member.class);
        Member createdMember = memberService.create(creatingMember);
        MemberResource responseResource = modelMapper.map(createdMember, MemberResource.class);
        return responseResource;
    }

    /**
     * メンバー更新
     * @param memberId
     * @param requestedResource
     * @return
     */
    @PutMapping("{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResource putMember(
            @PathVariable("memberId") String memberId,
            @RequestBody @Validated({PutMember.class, Default.class}) MemberResource requestedResource
    ) {
        Member updatingMember = modelMapper.map(requestedResource, Member.class);
        Member updatedMember = memberService.update(memberId, updatingMember);
        MemberResource responseResource = modelMapper.map(updatedMember, MemberResource.class);

        return responseResource;
    }

    /**
     * メンバー削除
     * @param memberId
     */
    @DeleteMapping("{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable("memberId") String memberId) {
        memberService.delete(memberId);
    }
}