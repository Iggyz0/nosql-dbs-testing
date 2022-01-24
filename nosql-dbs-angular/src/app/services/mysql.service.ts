import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FileModelMysql } from '../models/FileModelMysql';
import { UserModel } from '../models/UserModel';
import { WrapperFileUserMysql } from '../models/WrapperFileUserMysql';

@Injectable({
  providedIn: 'root'
})
export class MysqlService {

  constructor(private httpClient: HttpClient) { }

  url: string = "http://localhost:8080/files/";

  public writeFiles(wrapper: WrapperFileUserMysql): Observable<any> {
    const url = this.url + "writefilesmysql";
    return this.httpClient.post<WrapperFileUserMysql>(url, wrapper, {observe: "body"});
  }

  public readFiles(user: UserModel): Observable<FileModelMysql[]> {
    const url = this.url + "readfilesmysql";
    return this.httpClient.post<FileModelMysql[]>(url, user, {observe: "body"});
  }
}
