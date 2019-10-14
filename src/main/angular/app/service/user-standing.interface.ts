export interface UserStanding {
    userNickname: string;
    score: number;
    teamScores: TeamScore[];
}

export interface TeamScore {
    teamId: string;
    teamNickname: string;
    winOverUnder: number;
    wins: number;
    loses: number;
    wager: 'OVER' | 'UNDER';
    score: number;
}