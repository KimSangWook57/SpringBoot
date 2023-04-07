package edu.pnu.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
// 스택오버플로우 방지
@ToString(exclude = "boardList")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {

	@Id
	@Column(name = "MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	// 양방향 매핑 설정(일대다)
	// fetch=FetchType.EAGER (회원 정보 조회시 연관관계인 게시판 정보도 같이 조회한다.)
	// cascade = CascadeType.ALL (회원 객체가 영속화/수정/삭제시 회원 게시판도 같이 변경된다.) 
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();

}
