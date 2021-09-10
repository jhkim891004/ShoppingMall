package com.cndfactory.shoppingmall.web.controller.member;

import com.cndfactory.shoppingmall.domain.dto.member.MemberLoginDto;
import com.cndfactory.shoppingmall.domain.dto.member.MemberResponseDto;
import com.cndfactory.shoppingmall.domain.dto.member.MemberSaveDto;
import com.cndfactory.shoppingmall.domain.dto.member.MemberUpdateDto;
import com.cndfactory.shoppingmall.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberRestController {
	private final MemberService memberService;

	/**
	 * 개별 멤버 조회
	 * @param id
	 * @return
	 */
	@GetMapping("/api/v1/members/{id}")
	public ResponseEntity<MemberResponseDto> getOne(@PathVariable Long id) {
		return new ResponseEntity<>(memberService.getOne(id), HttpStatus.OK);
	}

	/**
	 * 멤버 모두 조회
	 * @param pageable
	 * @return
	 */
	@GetMapping("/api/v1/members")
	public ResponseEntity<Page<MemberResponseDto>> getAll(Pageable pageable) {
		return new ResponseEntity<>(memberService.getAll(pageable), HttpStatus.OK);
	}

	/**
	 * 멤버 수정
	 * @param dto
	 * @return
	 */
	@PutMapping("/api/v1/members")
	public ResponseEntity modify(@RequestBody @Valid MemberUpdateDto dto) {
		memberService.modify(dto);
		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * 멤버 삭제
	 * @param id
	 * @return
	 */
	@DeleteMapping("/api/v1/members")
	public ResponseEntity remove(@RequestParam Long id) {
		memberService.remove(id);
		return new ResponseEntity(HttpStatus.OK);
	}

}
