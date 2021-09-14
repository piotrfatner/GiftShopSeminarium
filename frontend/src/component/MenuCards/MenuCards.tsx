import React, {FC} from 'react';
import {faArrowDown, faArrowUp} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

import usePagination from "../Pagination/usePagination";
import ProductCardItem from "../PerfumeCardItem/ProductCardItem";
import PaginationItem from "../Pagination/PaginationItem";
import SearchForm from "../SearchForm/SearchForm";
import {Perfume} from "../../types/types";
import {ProductClass} from "../../types/ProductClass";
import Spinner from "../Spinner/Spinner";

type PropsType = {
    data: ProductClass[]
    loading: boolean
    itemsPerPage: number
    startFrom?: number
    searchByData: Array<{ label: string, value: string }>
    sortByPrice: boolean | undefined
    handleSortByPrice: (sortedBy: boolean, event: React.MouseEvent<HTMLAnchorElement, MouseEvent>) => void
};

const MenuCards: FC<PropsType> = ({data, loading, itemsPerPage, startFrom, searchByData, sortByPrice, handleSortByPrice}) => {
    const {
        slicedData,
        pagination,
        prevPage,
        nextPage,
        changePage,
        setFilteredData,
        setSearching
    } = usePagination({itemsPerPage, data, startFrom});

    return (
        <div className="container">
            {/*<div className="container-fluid mt-5 ml-2">
                <SearchForm
                    data={data}
                    searchByData={searchByData}
                    setFilteredData={setFilteredData}
                    setSearching={setSearching}/>
            </div>*/}
            <div className="container-fluid mt-3 ml-2">
                <div className="row">
                    <div className="col-md-6">
                        <PaginationItem
                            pagination={pagination}
                            prevPage={prevPage}
                            changePage={changePage}
                            nextPage={nextPage}/>
                    </div>
                    <div className="col-md-6 d-flex justify-content-end">
                        <ul className="pagination">
                            <li className="page-item disabled">
                                <a className="page-link" href="#">Sortuj po cenie</a></li>
                            <li className={sortByPrice ? "page-item active" : "page-item"}>
                                <a className={sortByPrice ?
                                    "page-link bg-light border-dark text-dark" :
                                    "page-link bg-dark border-dark text-light"}
                                   onClick={(event) => handleSortByPrice(true, event)}>
                                    <FontAwesomeIcon className="fa-sm" icon={faArrowDown}/>
                                </a>
                            </li>
                            <li className={sortByPrice ? "page-item" : "page-item active"}>
                                <a className={sortByPrice ?
                                    "page-link bg-dark border-dark text-light" :
                                    "page-link bg-light border-dark text-dark"}
                                   onClick={(event) => handleSortByPrice(false, event)}>
                                    <FontAwesomeIcon className="fa-sm" icon={faArrowUp}/>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                {loading ? <Spinner/> :
                <>
                <div>test123</div>
                    <div className="row">
                        {slicedData.map((product: ProductClass) => {
                            return (
                                <ProductCardItem
                                    key={product.id}
                                    perfume={product}
                                    colSize={3}
                                    link={"/product"}
                                    btnName={"SHOW MORE"}/>
                            );
                        })}
                    </div>
                    <PaginationItem
                        pagination={pagination}
                        prevPage={prevPage}
                        changePage={changePage}
                        nextPage={nextPage}/>
                </>
                }
            </div>
        </div>
    );
}

export default MenuCards;
