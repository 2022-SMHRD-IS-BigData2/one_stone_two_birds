insert into member (id, pwd, nickname, birthday, gender, living_area, manager)
values('manager','1234','testUser',str_to_date('20230223','%Y%m%d'),'female','운남동', 1);

select * from member;
select * from member_roles;
select * from role;
select * from login_record;

desc member;
desc drug_search_record;
desc login_record;
desc role;
desc member_roles;

insert into login_record
(id)
values('test');

drop table if exists drug_search_record;
drop table if exists hibernate_sequence;
drop table if exists login_record;
drop table if exists member;
drop table if exists member_id;



INSERT INTO member
(id, nickname, pwd)
VALUES('system', 'system', '$2a$10$6iJv7gmGXdP2Sf0ojKBfwuUgxaHqv0c3f50SfDNdxNxq.dSymAAlW');
 
INSERT INTO member
(id, nickname, pwd)
VALUES('user', 'user', '$2a$10$6iJv7gmGXdP2Sf0ojKBfwuUgxaHqv0c3f50SfDNdxNxq.dSymAAlW');
 


INSERT INTO role
(id, name)
VALUES(1, 'ROLE_SYSTEM');
 
INSERT INTO role
(id, name)
VALUES(2, 'ROLE_MEMBER');
 
INSERT INTO role
(id, name)
VALUES(3, 'ROLE_BOARD');

-- 데이터를 생성할 수 있는 권한 등록
INSERT INTO role
(id, name)
VALUES(4, 'OP_CREATE_DATA');
 
-- 데이터를 삭제할 수 있는 권한 등록
INSERT INTO role
(id, name)
VALUES(5, 'OP_DELETE_DATA');


INSERT INTO member_roles
(member_id, role_id)
VALUES('system', 1);
 
INSERT INTO member_roles
(member_id, role_id)
VALUES('user', 2);
 
INSERT INTO member_roles
(member_id, role_id)
VALUES('user', 3);

 
INSERT INTO member_roles
(member_id, role_id)
VALUES('system', 4);
 
-- B 관리자에게 SYSTEM 역할과 OP_DELETE_DATA 권한 등록
INSERT INTO member_roles
(member_id, role_id)
VALUES('test', 1);
 
INSERT INTO member_roles
(member_id, role_id)
VALUES('test', 1);

INSERT INTO member_roles
(member_id, role_id)
VALUES('netakasa@gmail.com', 1);

commit;