# Tailwind Setup

Execute following command

```
npm install -D tailwindcss postcss autoprefixer
```

```
npx tailwindcss init
```

paste following line in **angular.json** _style_ section

```
"node_modules/tailwindcss/base.css",
"node_modules/tailwindcss/components.css",
"node_modules/tailwindcss/utilities.css"
```

import following css in style.scss

``` scss
@import 'tailwindcss/base';
@import 'tailwindcss/components';
@import 'tailwindcss/utilities';
```

# Shared Path setup

Add following line in **tsconfig.json** _compilerOptions_ section

```
"baseUrl": "./",
"paths": {
      "@shared/*": ["src/app/shared/*"]
}
```

# Add typing file for avoiding javascript packages

First make typing folder in src and inside that folder, create **_typing.d.ts_** file.
Then add following line in **tsconfig.app.json** _include_ section

```
"src/typing/typing.d.ts"
```

# Make project non-standalone

Replace **main.ts** file with following code.

```
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));

```



# Remaining Package Import for package used in Shared folder code

```
npm i ngx-pagination
npm i ngx-dropzone-wrapper
npm i ngx-bootstrap
npm i ngx-infinite-scroll
npm i primeng
npm i @angular/material
npm i @ng-bootstrap/ng-bootstrap
npm i @angular/flex-layout
npm i chartjs-plugin-datalabels
npm i khalti-checkout-web
npm i sockjs-client
npm i @stomp/stompjs
```


