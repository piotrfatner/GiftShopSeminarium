import {ProductClass} from "../../types/ProductClass";
import {FilterParamsType} from "../../types/types";
class ProductService {
    async getProductById(): Promise<ProductClass> {
        const API_URL = `http://localhost:8080/api/v1/perfumes/idNew/2`;
        const response = await fetch(API_URL);
        if(!response.ok) {
            debugger;
        }
        const json = await response.json();
        let product = Object.assign(new ProductClass(), json);
        //const product2: Product = await response.json();
        /*response json to object?*/
        return product;
    }

    async getAllProducts(): Promise<ProductClass[]> {
        const API_URL = `http://localhost:8080/api/v1/perfumes/idNeww`;
        const response = await fetch(API_URL);
        const productsList = [];
        if(!response.ok) {
            debugger;
        }
        const json = await response.json();
        for (let x in json) {
            let product = Object.assign(new ProductClass(), json[x]);
            debugger;
            productsList.push(product);
        }
        //const product2: Product = await response.json();
        /*response json to object?*/
        return productsList;
    }



    async getProductsByPrices(filterParamsTypes: FilterParamsType): Promise<ProductClass[]> {
        const API_URL = `http://localhost:8080/api/v1/perfumes/searchProduct`;
        //const response = fetch(API_URL, post(filterParamsTypes));
        const productsList = [];
        const finalResponse = await fetch(API_URL, {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(filterParamsTypes)
        });
        const json = await finalResponse.json();
        for (let x in json) {
            let product = Object.assign(new ProductClass(), json[x]);
            debugger;
            productsList.push(product);
        }
        return productsList;
        //const product2: Product = await response.json();
        /*response json to object?*/
    }


}
export const post = (body: object): RequestInit => {
    return{
      method: 'post',
        /*headers: ...,*/
        body: JSON.stringify(body),
    };
};
export default new ProductService();