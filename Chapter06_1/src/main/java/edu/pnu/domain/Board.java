package edu.pnu.domain;

import java.util.Date;

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
public class Board {
	
	private Long seq;
	private Long cnt;
	private String content;
	private Date createDate;
	private String title;
	private String member_id;

}
