package edu.pnu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {
	@Id 
	@GeneratedValue
	// (strategy = GenerationType.IDENTITY)
	private Long seq;
	private String title;
	// 생성 후 수정 불가능
	@Column(updatable = false)
	private String writer;
	private String content;
	// 생성 후 삽입, 수정 불가능, 디폴트값 설정(h2 = date default now())
	@Column(insertable = false, updatable = false, columnDefinition = "date default now()")
	private Date createDate;
	// 생성 후 삽입, 수정 불가능, 디폴트값 설정
	@Column(insertable = false, updatable = false,
			columnDefinition = "number default 0")
	private Long cnt;
	
}
