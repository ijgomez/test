import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { StatusService } from '../status.service';

@Component({
  selector: 'app-servertimestamp',
  templateUrl: './servertimestamp.component.html',
  styleUrls: ['./servertimestamp.component.scss']
})
export class ServertimestampComponent implements OnInit, OnDestroy {

  @Input()
  interval = 1000;

  dateTime: any; // Date

  message: any;

  private timer;

  constructor(private statusService: StatusService) { }


  ngOnInit(): void {
    this.timer = setInterval(() => { this.reload(); }, this.interval);
  }

  ngOnDestroy(): void {
    clearInterval(this.timer);
  }

  reload(): void {
    this.statusService.serverTimestamp().subscribe(
      response => {
        this.dateTime = response.nano;
        this.message = null;
      },
      error => {
        this.message = error;
      }
    );
  }

}
