import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxColorsModule } from 'ngx-colors';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PublicRoutingModule } from './public/public-routing.module';
import { IonicModule, IonicRouteStrategy  } from '@ionic/angular';
import { RouteReuseStrategy } from '@angular/router';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import { AllMoodsComponent } from './components/all-moods/all-moods.component';
import { NgCalendarModule } from 'ionic2-calendar';
import { ProfilmoodComponent } from './components/profilmood/profilmood.component';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [AppComponent,AllMoodsComponent],
  entryComponents: [],
  imports: [
    BrowserModule, 
    IonicModule.forRoot(),
    AppRoutingModule, 
    HttpClientModule, 
    ReactiveFormsModule, 
    NgxColorsModule,
    PublicRoutingModule ,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatNativeDateModule,
    NgCalendarModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      "positionClass": "toast-bottom-full-width"
    })
  ],
    providers: [{ provide: {RouteReuseStrategy, LOCALE_ID} , useValue: 'de', useClass: IonicRouteStrategy }],  
  bootstrap: [AppComponent],
})
export class AppModule { }