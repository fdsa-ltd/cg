ALTER TABLE "t_user_role" DROP CONSTRAINT IF EXISTS "fk_id";
ALTER TABLE "t_user_role" ADD CONSTRAINT "fk_id" FOREIGN KEY( "user_id" )  REFERENCES "t_user"("id");
ALTER TABLE "t_user_role" DROP CONSTRAINT IF EXISTS "fk_id";
ALTER TABLE "t_user_role" ADD CONSTRAINT "fk_id" FOREIGN KEY( "role_id" )  REFERENCES "t_role"("id");
ALTER TABLE "t_user_role" DROP CONSTRAINT IF EXISTS "fk_cid";
ALTER TABLE "t_user_role" ADD CONSTRAINT "fk_cid" FOREIGN KEY( "cid" )  REFERENCES "t_company"("cid");
