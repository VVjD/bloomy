-- 사용자 테이블 생성
CREATE TABLE Member (
    seq number primary key,
	username	varchar2(30)    NOT NULL,
	password	varchar2(100)	NOT NULL,
	name	varchar2(20)	NOT NULL,
	nickname	varchar2(20)	NOT NULL,
	birth	date	NOT NULL,
	tel	varchar2(30)	NOT NULL,
	address	varchar2(100)	NOT NULL,
	email	varchar2(100)	NOT NULL,
	regdate	date	DEFAULT sysdate NOT NULL,
	role	varchar2(50) NOT NULL,
	image	varchar2(100) NOT NULL
);


create sequence seqMember;

select * from Member;
