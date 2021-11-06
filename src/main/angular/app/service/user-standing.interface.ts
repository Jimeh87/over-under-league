export interface UserStanding {
    userNickname: string;
    points: number;
    temperature: TeamTemperature;
    teamScores: TeamScore[];
}

export interface TeamScore {
    teamId: string;
    teamNickname: string;
    winOverUnder: number;
    wins: number;
    loses: number;
    wager: 'OVER' | 'UNDER';
    temperature: TeamTemperature;
    points: number;
}

export interface TeamTemperature {
    lastNumberOfGames: number;
    pointsInLastNumberOfGames: number;
    pointPercentage: number;
    temperature: 'HOT' | 'COLD' | 'NEUTRAL';
}
