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
    let pointOrPoints = 'points';
    if (Math.abs(temp.pointsInLastNumberOfGames) === 1) {
      pointOrPoints = 'point';
    }
    return `${pointsDescriptor}${Math.abs(temp.pointsInLastNumberOfGames)} ${pointOrPoints} in the last ${temp.lastNumberOfGames} games`;
  }

}
