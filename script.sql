-- 사용자 테이블 생성
CREATE TABLE Member (
    seq number primary key,
    username	varchar2(30)    UNIQUE,
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

-- 블로그 유형 테이블 생성
CREATE TABLE BlogType (
    seq	number	PRIMARY KEY ,
    blogTypeName	varchar2(50)	NOT NULL
);

CREATE SEQUENCE seqBlogType;
select * from BlogType;
insert into BlogType values (1, '엔터테이먼트/예술');
insert into BlogType values (2, '생활/노하우/쇼핑');
insert into BlogType values (3, '취미/여가/여행');
insert into BlogType values (4, '지식/동향');
commit;

-- 블로그 테이블 생성
CREATE TABLE Blog (
    username	varchar2(30)	NOT NULL,
    blogName	varchar2(50)	NOT NULL,
    blogIntro	varchar2(100)	NOT NULL,
    seqBlogType	number	NOT NULL,
    CONSTRAINT PK_BLOG PRIMARY KEY (username),
    CONSTRAINT FK_User_TO_Blog FOREIGN KEY (username) REFERENCES Member (username),
    CONSTRAINT FK_BlogType_TO_Blog FOREIGN KEY (seqBlogType) REFERENCES BlogType (seq)
);

select * from Blog;
insert into Blog values ('test', 'bloomy', 'bloomy입니다.', 4);

-- 메인 카테고리 테이블 생성
CREATE TABLE MainCategory (
    seq	number	NOT NULL,
    mainCategoryName	varchar2(30)	NOT NULL,
    mainCategorySecret	char(1)	DEFAULT 'n' NOT NULL,
    username	varchar2(30)	NOT NULL,
    CONSTRAINT PK_MAINCATEGORY PRIMARY KEY (seq),
    CONSTRAINT FK_tblBlog_TO_MainCategory FOREIGN KEY (username) REFERENCES Blog (username),
    CONSTRAINT MainCategory_main_secret_ck CHECK (mainCategorySecret in ('y', 'n'))
);

CREATE SEQUENCE seqMainCategory;

insert into MainCategory values (1, '자유', 'n', 'test');
insert into MainCategory values (2, '취미', 'n', 'test');
commit;

-- -- 서브 카테고리 테이블 생성
-- CREATE TABLE SubCategory (
--     seq	number NOT NULL,
--     subCategoryName	varchar2(30) NOT NULL,
--     subCategorySecret char(1) DEFAULT 'n' NOT NULL,
--     seqMainCategory	number	NOT NULL,
--     CONSTRAINT PK_SUBCATEGORY PRIMARY KEY (seq),
--     CONSTRAINT FK_MainCategory_TO_SubCategory FOREIGN KEY (seqMainCategory) REFERENCES MainCategory (seq),
--     CONSTRAINT SubCategory_sub_secret_ck CHECK (subCategorySecret in ('y', 'n'))
-- );
--
-- CREATE SEQUENCE seqSubCategory;

-- 게시글 테이블 생성
CREATE TABLE Board (
    seq	number	NOT NULL,
    username varchar2(30)	NOT NULL,
    boardTitle	varchar2(500)	NOT NULL,
    boardContent	clob	NOT NULL,
    boardRegdate	date	DEFAULT sysdate NOT NULL,
    boardViews	number	NOT NULL,
    boardSecret	char(1)	DEFAULT 'n' NOT NULL,
    seqMainCategory number	NOT NULL,
    seqSubCategory	number	NULL,
    CONSTRAINT PK_BOARD PRIMARY KEY (seq),
    CONSTRAINT FK_Member_TO_Board FOREIGN KEY (username) REFERENCES Member (username),
    CONSTRAINT FK_MainCategory_TO_Board FOREIGN KEY (seqMainCategory) REFERENCES MainCategory (seq),
    CONSTRAINT FK_SubCategory_TO_Board FOREIGN KEY (seqSubCategory) REFERENCES SubCategory (seq),
    CONSTRAINT Board_secret_ck CHECK (boardSecret in ('y', 'n'))
);

CREATE SEQUENCE seqBoard;

select * from Member;
