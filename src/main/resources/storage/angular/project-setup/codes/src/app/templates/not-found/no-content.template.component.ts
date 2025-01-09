import { Component, EventEmitter, OnInit, Output } from '@angular/core';


@Component({
  standalone: false,
  selector: 'no-content-template',
  template: `
  <div class="flex flex-col  items-center  " >
    <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAABQCAYAAACOEfKtAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAASvSURBVHgB7ZvbldM8EMf/3+WdUAFDBYQKEBWwHRAqIFSwoQKggoQKWCqIqWChgjUVLLzxBh4soYnX3nhkOUZe/c7RsexIvoznJlkBMplMJpPJTMN/mAdUFVOVH1X51tFmYX/PWExV3lTlqio/bbnqaOvaXFZla/veWUxV9vBCk6VNgItb2q5whyC0C+4atVatbZs2VlX5gENtvVOCXOLmw7MwDfRQVTYt5zvHTCHUWiY1xiAOm8a5ZylEqSk71D4tJtS4xhozgjXCPdiHHu1ZuOaW/S4IXojXiP+SJsM9FG+pR/tL236LQ6H00SoD/7I2mAEcONwDbXv2ucRhdNYKxAl8jxlwBi8A07MP4WZ0ZaH2NckdvPB78S/+XhYddS0Fuod3fa6dLNKE+0ZGacI/B/SfhQZ+htecZ9DBfZ+K/tSjD7dZ2vonzIQNdEkuoR6WLTr2u+Dfpe88w0xoPtgYvonPKU1/FhFYwg+k8ksKDG5OhxFmhJyKuuj4PQSDmzM7sxMeY9AeSfm4E8De/sbHugRKqH0hT8DKBFuaLWGGvIV/SI6QhO7J1KY2cbk+0o5/n9XkQRPp/7Y4Lri+xWnt4KD0D/TwRZc923Ie9hlh8HWubznva9QayvdiUGvnI7SbIt/DV7u9gH5kEg32Idq3zabUV+ASg3aT2yDRoVaI8OSDa4VokIDgNCbMmkRVKavyvmcfbv/c1suqPIbOfFaohbbDhGYXC6cNW+hYY8ZZvgaXElxAzx7t+VzyhJhwgXqmQ4Mbb5LdL3F62AW8QHhWMBg34L5EGHJ+b6ryFpH5X9HWOfHQSMhv/hXqoHLqaEp2ew8TsoPP61IjNAAeJWRGmpD5g0aAyedhYxAqwAfI/OauaGBpt9E/FoVEYeY+6tmNVHgIPwyNSqgGpjgbUmIEQgVIyPwmtg8ktC+jTalcQWFhsU3YIH3tJChGLKFBpEuAcgnGDmmxstsSIwbIY4NyN+W1R1oQAod72qGc08I2FTfwmpna4hwj6qp7DxXgsZsokBbS9RQYEY5QXSa6h/8AlBruudQzTVoNLDuOy9XwqZmvW/HAFFASasLUOG5EPeSbyZQYUf8IJVoBfu84Ln3IZN8cAnki6up71+SBEmrsG7stlTexhP/g7paBlNDDLuRM7Jfob45fbN/Qa6vYwOdLDkJYDkVoH0aF0La43Cj6c9ugCZIhi8wX4uIOjQ/5hnhzjEPPU4SeQ2vCpajfsxcN9X/cl5d6GHEsNADx9152Be6llvhLc9EVvIk43+WGb6Hfi5NmSB7ocr9Uh29RGDKUa0a+1PK/c9RBa40TQvAmvMLhoqGxWNhrbeyWEAc3fDup6yF4gZ1j/GVrL9G+UPwcw5DrdDSpVxTchWXuNYYZGNw+9b5COOtI5xmUBy5FvcC48IJyXlLH6UqM3PGJqBc4Mc53DB099EEO9RyEsEXrEucWBt976FhY8gXj0ZaYlxiGgU+9CgwkxITLxn5q6YvU3klyV5m6cCGkhbx/wgTscBiJU4IQ2XcP9YEcEVdIA/Z7L8X+O0zEBuMsqThluUIkYgSR1OCg9xiRCPm3JmOQXvBgd1MgL1XOZDKZTGYm/ALN1FX5+y3/UgAAAABJRU5ErkJggg==" class="sc-kOHTFB bSxpkO">
    No data
  </div>
  `,
  styles: [
],
})
export class NoContentComponent{

    constructor() { }
  
    
}
