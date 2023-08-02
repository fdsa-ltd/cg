alter table t_user_role drop constraint fk_id;
alter table t_user_role add constraint fk_id foreign key( user_id )  references t_user(id);
alter table t_user_role drop constraint fk_id;
alter table t_user_role add constraint fk_id foreign key( role_id )  references t_role(id);
alter table t_user_role drop constraint fk_cid;
alter table t_user_role add constraint fk_cid foreign key( cid )  references t_company(cid);
