import { RouterModule, RouterOutlet, Routes } from '@angular/router';
import { FeaturesLayoutComponent } from './layouts/features-layout/features-layout.component';
import { LoginLayoutComponent } from './layouts/login-layout/login-layout.component';
import { NgModule } from '@angular/core';

export const routes: Routes = [
      {path : '', redirectTo: '/_', pathMatch: 'full'},
  {
    path: '',
    component: LoginLayoutComponent,
    children: [
      {
        path: '',
        redirectTo: '/',
        pathMatch: 'full'
      },
      {
        path: '',
        loadChildren: () => import('./_auth/auth.module').then(m => m.AuthModule)
      }
    ]
  },
  {
    path: '',
    component: FeaturesLayoutComponent,
    children: [
      {
        path: '',
        loadChildren: () => import('./features/features.module').then(m => m.FeaturesModule)
      }
    ]
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
