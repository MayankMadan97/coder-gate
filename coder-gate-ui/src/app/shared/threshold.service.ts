import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ThresholdDTO } from "../main/dashboard/dashboard.component";

@Injectable({
    providedIn: "root"
})

export class ThresholdService {
    constructor (private http: HttpClient) { }

    postThresholdValues(values: any, repoID: number): Observable<any> {
        let thresoldApiEndpoint: string = "http://localhost:3000/threshold/" + repoID;
        console.log(thresoldApiEndpoint);
        return this.http.post(thresoldApiEndpoint, values);
    }

    getThresholdValues(repoID: number): Observable<ThresholdDTO> {
        let thresholdApiEndpoint: string = "http://localhost:3000/threshold/" + repoID;
        console.log(thresholdApiEndpoint);
        return this.http.get<ThresholdDTO>(thresholdApiEndpoint);
    }
}