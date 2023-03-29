import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ThresholdDTO } from "../main/dashboard/dashboard.component";

@Injectable({
    providedIn: "root"
})

export class ThresholdService {
    constructor (private http: HttpClient) { }

    postThresholdValues(values: any): Observable<any> {
        return this.http.post("http://localhost:3000/threshold/618842221", values);
    }

    getThresholdValues(): Observable<ThresholdDTO> {
        return this.http.get<ThresholdDTO>("http://localhost:3000/threshold/618842221");
    }
}