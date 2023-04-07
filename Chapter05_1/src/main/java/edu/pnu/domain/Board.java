package edu.pnu.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
// 스택오버플로우 방지
@ToString(exclude = "member")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
//	private String writer;
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;
	// 연관 매핑 설정(다대일)
	// nullable = false는 참조 키가 항상 값을 가진다는 뜻이다.
	// (outer -> inner로 바뀐 것, 콘솔에는 inner/outer가 찍히지 않음)
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID", nullable = false)
	private Member member;
	// 회원 객체를 설정할 때, member의 BoardList에 게시글을 추가하기 위한 코드
	public void setMember(Member member) { 
		this.member = member;
		member.getBoardList().add(this);
	}
	
}
