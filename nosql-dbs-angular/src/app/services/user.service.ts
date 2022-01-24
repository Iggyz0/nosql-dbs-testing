import { Injectable } from '@angular/core';
import { UserModel } from '../models/UserModel';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  user: UserModel;

  constructor() {
    this.user = {
      name: "igor"
    };
  }
}
