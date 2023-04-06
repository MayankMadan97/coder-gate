import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ThresholdDTO } from "../main/dashboard/dashboard.component";
import { BACKEND_URL } from '../app.constants';

@Injectable({
    providedIn: "root"
})

export class ThresholdService {
    private baseUrl = BACKEND_URL;

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
