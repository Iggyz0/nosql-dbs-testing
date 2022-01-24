import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FileModel } from '../models/FileModel';
import { UserModel } from '../models/UserModel';
import { WrapperFileUser } from '../models/WrapperFileUser';

@Injectable({
  providedIn: 'root'
})
export class MongodbService {

  constructor(private httpClient: HttpClient) { }

  url: string = "http://localhost:8080/files/";

  public writeFiles(wrapper: WrapperFileUser): Observable<any> {
    const url = this.url + "writefilesmongo";
    return this.httpClient.post<WrapperFileUser>(url, wrapper, { observe: "body" });
  }

  public readFiles(user: UserModel): Observable<FileModel[]> {
    const url = this.url + "readfilesmongo";
    return this.httpClient.post<FileModel[]>(url, user, { observe: "body" });
  }
}
