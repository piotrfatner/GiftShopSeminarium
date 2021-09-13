import {Category, PerfumePrice} from "../../types/types";

export const perfumer: Array<{ name: string }> = [
    {"name": "Burberry"},
    {"name": "Bvlgari"},
    {"name": "Calvin Klein"},
    {"name": "Carolina Herrera"},
    {"name": "Chanel"},
    {"name": "Creed"},
    {"name": "Dior"},
    {"name": "Dolce&Gabbana"},
    {"name": "Giorgio Armani"},
    {"name": "Gucci"},
    {"name": "Hermes"},
    {"name": "Hugo Boss"},
    {"name": "Jean Paul Gaultier"},
    {"name": "Lancome"},
    {"name": "Paco Rabanne"},
    {"name": "Prada"},
    {"name": "Tom Ford"},
    {"name": "Versace"},
];

export const gender: Array<{ name: string }> = [
    {"name": "male"},
    {"name": "female"},
];

export const price: Array<PerfumePrice> = [
    {"id": 1, "name": "any", "array": []},
    {"id": 2, "name": "15 - 25 $", "array": [15, 25]},
    {"id": 3, "name": "25 - 40 $", "array": [25, 40]},
    {"id": 4, "name": "40 - 90 $", "array": [40, 90]},
    {"id": 5, "name": "90 - 175+ $", "array": [90, 250]}
];

export const categories: Array<Category> = [
    {"id": 1, "name": "Bizuteria", "checked": true, "array": [{"id": 5, "name": "Kolczyki","checked": true}, {"id": 6, "name": "Bransoletki","checked": true}, {"id": 7, "name": "Pierścionki","checked": true}, {"id": 8, "name": "Naszyjniki","checked": true}]},
    {"id": 2, "name": "Zegarki", "checked": true, "array": [{"id": 9, "name": "Lorus","checked": true}, {"id": 10, "name": "Casio","checked": true}, {"id": 11, "name": "Timex","checked": true}, {"id": 12, "name": "Timemaster","checked": true}]},
    {"id": 3, "name": "Perfumy", "checked": true, "array": [{"id": 13, "name": "Armani","checked": true}, {"id": 14, "name": "Hugo Boss","checked": true}, {"id": 15, "name": "Chanel","checked": true}, {"id": 16, "name": "Calvin Klein","checked": true}]},
    {"id": 4, "name": "Torebki", "checked": true, "array": [{"id": 17, "name": "Kuferek","checked": true}, {"id": 18, "name": "Nerka","checked": true}, {"id": 19, "name": "Kopertówka","checked": true}, {"id": 20, "name": "Listonoszka","checked": true}]},
];
