import React, {FC, useEffect, useState} from "react";
import {Route, useLocation} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";

import Checkbox from "../../component/CheckBox/Checkbox";
import CheckboxRadio from "../../component/CheckboxRadio/CheckboxRadio";
import MenuCards from "../../component/MenuCards/MenuCards";
import {categories, gender, perfumer, price} from "./MenuData";
import {
    fetchPerfumes,
    fetchPerfumesByFilterParams,
    fetchPerfumesByGender,
    fetchPerfumesByPerfumer
} from "../../redux/thunks/perfume-thunks";
import "./MenuStyle.css";
import {AppStateType} from "../../redux/reducers/root-reducer";
import {FilterParamsType, Perfume, Product} from "../../types/types";
import {ProductClass} from "../../types/ProductClass";
import ProductService from "../../redux/services/ProductService";
import ScrollButton from "../../component/ScrollButton/ScrollButton";
import CheckBoxWithSubcategories from "../../component/CheckBox/CheckboxWithSubcategories";

const Menu: FC = () => {
    const dispatch = useDispatch();
    const perfumes: Array<Perfume> = useSelector((state: AppStateType) => state.perfume.perfumes);
    const [products, setProducts] = useState(new ProductClass());
    const [productsList, setProductsList] = useState([new ProductClass()]);
    const loading: boolean = useSelector((state: AppStateType) => state.perfume.isPerfumeLoading);
    const [filterParams, setFilterParams] = useState<FilterParamsType>({
        perfumers: [],
        genders: [],
        prices: [],
        categories: []
    });
    const [sortByPrice, setSortByPrice] = useState<boolean>();
    const {state} = useLocation<{ id: string }>();

    /*const getPerfumeById = () => {
        const object;
        //object = ProductService.getProductByIdHeader('2');
    };*/

    useEffect(() => {
        //getPerfumeById();
        //setProducts(ProductService.getProductById());
        ProductService.getProductById().then((a) => {setProducts(a)});
        ProductService.getAllProducts().then((b) => {setProductsList(b)});
    }, []);
    useEffect(() => {
        const perfumeData: string = state.id;

        if (perfumeData === "female" || perfumeData === "male") {
            dispatch(fetchPerfumesByGender({perfumeGender: perfumeData}));
            window.scrollTo(0, 0);
        } else if (perfumeData === "all") {
            dispatch(fetchPerfumes());
            window.scrollTo(0, 0);
        } else {
            dispatch(fetchPerfumesByPerfumer({perfumer: perfumeData}));
            window.scrollTo(0, 0);
        }
    }, []);

    const getProducts = (variables: FilterParamsType): void => {
        //dispatch(fetchPerfumesByFilterParams(variables));
        ProductService.getProductsByPrices(variables).then((a) => {setProductsList(a)});
    };

    const handlePrice = (value: number): Array<number> => {
        let find = price.find((item) => item.id == value);
        return find!.array;
    };

    const handleFilters = (filters: Array<string> | number | Array<boolean>, category: string): void => {
        const newFilters: any = filterParams;
        newFilters[category] = filters;

        if (category === "prices") {
            let priceValues = handlePrice(filters as number);
            newFilters[category] = priceValues;
        }
        getProducts({...newFilters, sortByPrice})
        setFilterParams(newFilters);
    };

    const handleSortByPrice = (sortedBy: boolean, event: React.MouseEvent<HTMLAnchorElement, MouseEvent>): void => {
        event.preventDefault();

        setSortByPrice(sortedBy);
        getProducts({...filterParams, sortByPrice: sortedBy});
    };

    return (
        <div className="container d-flex">
            <ScrollButton/>
            <nav id="sidebar">
                <ul className="list-unstyled components">
                    <h5>Kategorie</h5>
                    <li className="active mb-2">
                        <CheckBoxWithSubcategories list={categories}
                                  handleFilters={(filters) => handleFilters(filters, "categories")}/>
                    </li>
                    <h5>Cena</h5>
                    <li className="active mb-2">
                        <CheckboxRadio list={price}
                                       handleFilters={(filters) => handleFilters(filters, "prices")}/>
                    </li>
                </ul>
            </nav>
            <Route exact component={() =>
                <MenuCards
                    data={productsList}
                    loading={loading}
                    itemsPerPage={20}
                    searchByData={[
                        {label: 'Brand', value: 'perfumer'},
                        {label: 'Perfume title', value: 'perfumeTitle'},
                        {label: 'Manufacturer country', value: 'country'}]}
                    sortByPrice={sortByPrice}
                    handleSortByPrice={handleSortByPrice}/>}/>
            <div id="test">

            </div>
        </div>
    );
};

export default Menu;
