import { AfterViewInit, Component, OnInit, QueryList, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { FileModel } from '../models/FileModel';
import { FileModelMysql } from '../models/FileModelMysql';
import { FileModelNeo4j } from '../models/FileModelNeo4j';
import { UserModel } from '../models/UserModel';
import { WrapperFileUser } from '../models/WrapperFileUser';
import { WrapperFileUserMysql } from '../models/WrapperFileUserMysql';
import { WrapperFileUserNeo4j } from '../models/WrapperFileUserNeo4j';
import { MongodbService } from '../services/mongodb.service';
import { MysqlService } from '../services/mysql.service';
import { Neo4jService } from '../services/neo4j.service';
import { UserService } from '../services/user.service';

export interface Readings {
  db: string,
  numberOfFiles: number,
  time: string,
  type?: string
}

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit, AfterViewInit {

  mysqlMessage: string = "";
  mongoMessage: string = "";
  neo4jMessage: string = "";
  
  numberOfFilesToWrite: number = 100;
  disableInput: boolean = false;

  timesReadings: Readings[] = [];

  user: UserModel = new UserModel();

  readingsDataSource = new MatTableDataSource<Readings>();
  displayedColumnsOrders = [
    "No.",
    "db",
    "numberOfFiles",
    "time",
    "type"
  ];

  @ViewChild(MatTable) table! : MatTable<any>;
  @ViewChild(MatSort) sort: MatSort = new MatSort;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private mysql: MysqlService, private mongodb: MongodbService, private neo4j: Neo4jService, private userService: UserService) { }

  ngOnInit(): void {
    this.user.name = this.userService.user.name;
    this.readingsDataSource.data = this.timesReadings;
  }

  ngAfterViewInit(){
    this.readingsDataSource.sort = this.sort;
    this.readingsDataSource.paginator = this.paginator;
  }

  readInputProperty() {
    console.log(this.numberOfFilesToWrite);
  }

  logResults() {
    console.table(this.timesReadings);
  }

  doFilterReadings(filterValue: string) {
    this.readingsDataSource.filter = filterValue.trim();
  }

  // ------------------------------------------------------------------------ MySQL
  public writeFilesMysql(): any {
    this.disableInput = true;
    this.mysqlMessage = "Writing files to a MySQL database...";
    let files: FileModelMysql[] = [];

    for (let i = 0; i < this.numberOfFilesToWrite; i++) {
      let fileModel: FileModelMysql = {
        file_name: "File " + i
      }

      files.push(fileModel);
    }

    let wrapper: WrapperFileUserMysql = {
      files: files,
      user: this.userService.user
    }

    const start = new Date().getTime();

    this.mysql.writeFiles(wrapper).subscribe(response => {
      let elapsed = (new Date().getTime() - start)/1000;
      this.timesReadings.push({
        db: "MySQL",
        numberOfFiles: wrapper.files!.length,
        time: elapsed.toString(),
        type: "Writing"
      });
      
      this.mysqlMessage = "Files written.";
      this.disableInput = false;
      this.readingsDataSource.data = this.timesReadings;
    });
  }

  public readFilesMysql(): any {
    this.disableInput = true;
    this.mysqlMessage = "Reading files from a MySQL database...";
    const start = new Date().getTime();
    this.mysql.readFiles(this.user).subscribe(response => {
      let elapsed = (new Date().getTime() - start)/1000;
      this.timesReadings.push({
        db: "MySQL",
        numberOfFiles: response.length,
        time: elapsed.toString(),
        type: "Reading"
      });
      
      this.mysqlMessage = "All files read.";
      this.disableInput = false;
      this.readingsDataSource.data = this.timesReadings;
    })
  }

  // ---------------------------------------------------------------------- MongoDB
  public writeFilesMongo(): any {
    this.disableInput = true;
    this.mongoMessage = "Writing files to a MongoDB...";
    let files: FileModel[] = [];

    for (let i = 0; i < this.numberOfFilesToWrite; i++) {
      let fileModel: FileModel = {
        fileName: "File " + i
      }

      files.push(fileModel);
    }

    let wrapper: WrapperFileUser = {
      files: files,
      user: this.userService.user
    }

    const start = new Date().getTime();

    this.mongodb.writeFiles(wrapper).subscribe(response => {
      let elapsed = (new Date().getTime() - start)/1000;
      this.timesReadings.push({
        db: "MongoDB",
        numberOfFiles: wrapper.files!.length,
        time: elapsed.toString(),
        type: "Writing"
      });
      
      this.mongoMessage = "Files written.";
      this.disableInput = false;
      this.readingsDataSource.data = this.timesReadings;
    });
  }

  public readFilesMongo(): any {
    this.disableInput = true;
    this.mongoMessage = "Reading files from a MongoDB..."
    const start = new Date().getTime();
    this.mongodb.readFiles(this.user).subscribe(response => {
      let elapsed = (new Date().getTime() - start)/1000;
      this.timesReadings.push({
        db: "MongoDB",
        numberOfFiles: response.length,
        time: elapsed.toString(),
        type: "Reading"
      });
      
      this.mongoMessage = "All files read.";
      this.disableInput = false;
      this.readingsDataSource.data = this.timesReadings;
    })
  }

  //  ------------------------------------------------------------------ Neo4j
  public writeFilesNeo4j(): any {
    this.disableInput = true;
    this.neo4jMessage = "Writing files to a Neo4j Graph database...";
    let files: FileModelNeo4j[] = [];

    for (let i = 0; i < this.numberOfFilesToWrite; i++) {
      let fileModel: FileModelNeo4j = {
        name: "File " + i
      }

      files.push(fileModel);
    }

    let wrapper: WrapperFileUserNeo4j = {
      files: files,
      user: this.userService.user
    }

    const start = new Date().getTime();
    this.neo4j.writeFiles(wrapper).subscribe(response => {
      let elapsed = (new Date().getTime() - start)/1000;
      this.timesReadings.push({
        db: "Neo4j",
        numberOfFiles: wrapper.files!.length,
        time: elapsed.toString(),
        type: "Writing"
      });
      
      this.neo4jMessage = "Files written.";
      this.disableInput = false;
      this.readingsDataSource.data = this.timesReadings;
    })
  }

  public readFilesNeo4j(): any {
    this.disableInput = true;
    this.neo4jMessage = "Reading files from a Neo4j database..."
    const start = new Date().getTime();
    this.neo4j.readFiles(this.user).subscribe(response => {
      let elapsed = (new Date().getTime() - start)/1000;
      this.timesReadings.push({
        db: "Neo4j",
        numberOfFiles: response.length,
        time: elapsed.toString(),
        type: "Reading"
      });
      
      this.neo4jMessage = "All files read.";
      this.disableInput = false;
      this.readingsDataSource.data = this.timesReadings;
    })
  }
}
