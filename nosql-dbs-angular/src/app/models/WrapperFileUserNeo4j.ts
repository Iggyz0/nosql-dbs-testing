import { FileModelNeo4j } from "./FileModelNeo4j";
import { UserModelNeo4j } from "./UserModelNeo4j";

export class WrapperFileUserNeo4j {
    user?: UserModelNeo4j;
    files?: FileModelNeo4j[];
}