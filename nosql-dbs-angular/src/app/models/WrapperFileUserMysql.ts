import { FileModelMysql } from "./FileModelMysql";
import { UserModel } from "./UserModel";

export class WrapperFileUserMysql {
    user?: UserModel;
    files?: FileModelMysql[];
}