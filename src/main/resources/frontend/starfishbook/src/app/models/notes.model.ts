import { Categories } from "./categories.model";

export class Notes {
    id?: number;
    title?: string;
    text?: string;
    color?: string;
    categories?: Categories;
}