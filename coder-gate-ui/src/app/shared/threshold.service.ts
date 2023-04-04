import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ThresholdDTO } from "../main/dashboard/dashboard.component";

@Injectable({
    providedIn: "root"
})

export class ThresholdService {
    private baseUrl = 'http://localhost:3000';

    constructor (private http: HttpClient) { }

    postThresholdValues(values: any,repositoryId : number): Observable<any> {
        const url = `${this.baseUrl}/threshold/${repositoryId}`;
        console.log("Url for post threshold",url);
        return this.http.post(url, values);
    }

    getThresholdValues(repositoryID : number ): Observable<ThresholdDTO> {
        const url = `${this.baseUrl}/threshold/${repositoryID}`;

        return this.http.get<ThresholdDTO>(url);

    }
}
