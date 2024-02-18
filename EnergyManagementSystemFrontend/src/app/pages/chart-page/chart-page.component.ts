import {Component, OnInit} from '@angular/core';
import {Device} from "../../models/device/Device";
import {Router} from "@angular/router";
import {DeviceService} from "../../services/device/device.service";
import jwt_decode from "jwt-decode";
import {SensorService} from "../../services/monitoring/sensor.service";
import {Sensor} from "../../models/monitoring/Sensor";
import Chart from 'chart.js/auto';


@Component({
  selector: 'app-chart-page',
  templateUrl: './chart-page.component.html',
  styleUrls: ['./chart-page.component.css']
})
export class ChartPageComponent implements OnInit{
  dateValue: Date =  new Date()
  deviceIDList: any = []
  deviceIDSelect: any
  chart = new Chart("chart", {
    type: 'bar',
    data: {
      labels: [],
      datasets: [{
        label: 'Total Hourly Consumption',
        data: [],
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1
      }]
    },
    options: { aspectRatio: 2.5}
  });
  sensorTimestampList: any = []
  sensorTotalHourlyConsumptionList: any = []


  constructor(private deviceService: DeviceService,
              private sensorService: SensorService,
              private router: Router
  ){  }

  ngOnInit(): void {
    const user: any = localStorage.getItem("token")
    var tokenLoad: any;
    tokenLoad = jwt_decode(user)
    console.log(tokenLoad.clientID)
    this.deviceService.getAllDevicesByClientID(tokenLoad.clientID).subscribe(
      (result: Device[]) => {
        for (let index = 0; index < result.length; index++) {
          const selectedItem = result[index];
          this.deviceIDList.push(selectedItem.id);
        }
        console.log(this.deviceIDList)
      },
      (_error: Error) => {
        console.log("error");
      }
    );
  }

  onChange(event: any){
    console.log(event)
    this.dateValue = event.target.value
  }

  createChart(sensorTimestampList: any, sensorTotalHourlyConsumptionList: any){
    this.chart.destroy()
    this.chart = new Chart("chart" , {
      type: 'bar',
      data: {
        labels: this.sensorTimestampList,
        datasets: [{
          label: 'Total Hourly Consumption',
          data: this.sensorTotalHourlyConsumptionList,
          backgroundColor: 'rgba(75, 192, 192, 0.2)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1
        }]
      },
      options: { aspectRatio: 2.5}
    });

  }

  seeChart(){
    console.log(this.dateValue)
    this.sensorTimestampList = []
    this.sensorTotalHourlyConsumptionList = []
    this.sensorService.getAllSensorByDeviceIDAndTimestamp(this.deviceIDSelect, this.dateValue).subscribe(
      (result: Sensor[]) => {
        result.forEach((sensor) => {
          this.sensorTimestampList.push(sensor.timestamp);
          this.sensorTotalHourlyConsumptionList.push(sensor.totalHourlyConsumption)
        });
        console.log(this.sensorTimestampList)
        console.log(this.sensorTotalHourlyConsumptionList)
        this.createChart(this.sensorTimestampList, this.sensorTotalHourlyConsumptionList);
      },
      (_error: Error)=> {
        console.log("error");
      }
    )
  }

  logOut(): void {
    localStorage.clear()
    this.router.navigateByUrl("/firstPage")
  }

}
