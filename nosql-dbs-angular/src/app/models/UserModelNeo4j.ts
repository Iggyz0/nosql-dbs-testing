import { FileModelNeo4j } from "./FileModelNeo4j";

export class UserModelNeo4j {
    id?: string;
    name?: string;
    files?: FileModelNeo4j[];
}