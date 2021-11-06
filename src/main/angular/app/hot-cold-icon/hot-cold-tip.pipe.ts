import { Pipe, PipeTransform } from '@angular/core';
import {TeamTemperature} from "../service/user-standing.interface";

@Pipe({
  name: 'hotColdTip'
})
export class HotColdTipPipe implements PipeTransform {

  transform(temp: TeamTemperature): any {
    let pointsDescriptor = '';
    if (temp.pointsInLastNumberOfGames < 0) {
      pointsDescriptor = 'Lost ';
    } else if (temp.pointsInLastNumberOfGames > 0) {
      pointsDescriptor = 'Gained ';
    }
    return `${pointsDescriptor}${Math.abs(temp.pointsInLastNumberOfGames)} points in the last ${temp.lastNumberOfGames} games`;
  }

}
