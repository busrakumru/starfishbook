import { Component, OnInit, ViewChild } from '@angular/core';
import { CalendarComponent } from 'ionic2-calendar';
import { Todo } from '../models/todo.model';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss']
})

//@ViewChild(CalendarComponent) myCalendar: CalendarComponent;

export class Tab3Page implements OnInit {


  eventSource;
  todos: Todo = new Todo();
  viewTitle: string;
  minDate = new Date().toISOString();

  /** calendar format */
  calendar = {
    startingDayMonth: "1",
    formatDayHeader: 'EEEEEE',
    formatDay: 'dd',
    mode: 'month',
    currentDate: new Date(),
    queryMode: 'remote',
   
  };

  constructor() {
   
  }
  ngOnInit(): void {
   this.loadEvents();
  }

  onRangeChanged(ev) {
    console.log('range changed: startTime: ' + ev.startTime + ', endTime: ' + ev.endTime);
  }

  /** function for sliding back in the calendar */
  async back() {
    var swiper = document.querySelector('.swiper-container')['swiper'];
    swiper.slidePrev();
  }

  /** function for sliding forward in the calendar */
  async next() {
    var swiper = document.querySelector('.swiper-container')['swiper'];
    swiper.slideNext();
  }

  async onViewTitleChanged(title: string) {
    this.viewTitle = title;
  }

  onEventSelected(event) {
    console.log('Event selected:' + event.startTime + '-' + event.endTime + ',' + event.title);
}
onTimeSelected(ev) {
  console.log('Selected time: ' + ev.selectedTime + ', hasEvents: ' +
      (ev.events !== undefined && ev.events.length !== 0) + ', disabled: ' + ev.disabled);
}
  loadEvents() {
    this.eventSource = this.todos;
}


}