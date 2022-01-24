import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FileModelNeo4j } from '../models/FileModelNeo4j';
import { UserModelNeo4j } from '../models/UserModelNeo4j';
import { WrapperFileUserNeo4j } from '../models/WrapperFileUserNeo4j';

@Injectable({
  providedIn: 'root'
})
export class Neo4jService {

  constructor(private httpClient: HttpClient) { }

  url: string = "http://localhost:8080/files/";

  public writeFiles(wrapper: WrapperFileUserNeo4j): Observable<WrapperFileUserNeo4j> {
    const url = this.url + "writefilesneo4j";
    return this.httpClient.post<WrapperFileUserNeo4j>(url, wrapper, {observe: "body"});
  }

  public readFiles(user: UserModelNeo4j): Observable<FileModelNeo4j[]> {
    const url = this.url + "readfilesneo4j";
    return this.httpClient.post<FileModelNeo4j[]>(url, user, {observe: "body"});
  }
}
