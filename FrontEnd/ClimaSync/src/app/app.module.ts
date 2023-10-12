import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { SlideshowComponent } from './slideshow/slideshow.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { AboutClimasyncComponent } from './about-climasync/about-climasync.component';
import {NgOptimizedImage} from "@angular/common";
import { FooterComponent } from './footer/footer.component';
import { CardDescriptionComponent } from './card-description/card-description.component';
import {FormsModule} from "@angular/forms";
import {HttpClient, HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    NavBarComponent,
    SlideshowComponent,
    UserLoginComponent,
    AboutClimasyncComponent,
    FooterComponent,
    CardDescriptionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgOptimizedImage,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
