import { Categories } from "./categories.model";

export class Notes {
    id?: number;
    title: string;
    text: string;
    color: string;
    files: Array<any>;
    //categories?: Categories;
}