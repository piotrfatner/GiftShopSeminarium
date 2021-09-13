import React, {FC, useState} from 'react'
import "../../pages/Menu/MenuStyle.css";
import {Category, SubCategory} from "../../types/types";

type PropsType = {
    handleFilters: (filters: Array<string>) => void
    list: Array<Category>
};

const CheckBoxWithSubcategories: FC<PropsType> = ({handleFilters, list}) => {
    const [checked, setChecked] = useState<Array<string>>([]);
    const [checkedList, setCheckedList] = useState<Array<Category>>(list);
    const [checkedState, setCheckedState] = useState(
        new Array(21).fill(true)
    );

    const handleToggle = (value: string): void => {
        const currentIndex: number = checked.indexOf(value);
        const newChecked: Array<string> = [...checked];
        debugger;
        if (currentIndex === -1) {
            newChecked.push(value)
        } else {
            newChecked.splice(currentIndex, 1)
        }
        setChecked(newChecked);
        let sublist = Array<SubCategory>();
        const categoryListMap: Category = list.filter(x => x.name === value)[0];
        if(categoryListMap !== null && categoryListMap !== undefined){
            const sublistMap: SubCategory[] = categoryListMap.array; // cannot read property array of undefined
            //const newChecked2: Array<string> = [...checked];
            for (let xx in sublistMap){
                newChecked.push(sublistMap[xx].name);
            }
            setChecked(newChecked);
        }

        //sublist.push(list.filter(x => x.name === value));
        // react makes changes immediately judging by const values
        // category and subcategory types should have checked values?

        handleFilters(newChecked)
    };
    const handleToggle2 = (value: number): void => {
        const prevState = checkedState[value];
        if(value < 5){

            const categoryListMap: Category = list.filter(x => x.id === value)[0];
            const sublistMap: SubCategory[] = categoryListMap.array;
            for (let sublist99 in sublistMap){
                checkedState[sublistMap[sublist99].id] = !prevState;
            }
        }
        /*const updatedCheckedState = checkedState.map((item, index) =>
            index === value ? !item : item
        );

        setCheckedState(updatedCheckedState);*/
        checkedState[value] = !prevState;
        handleFilters(checkedState)
    };

    const renderCheckboxLists = () => checkedList && checkedList.map((value: Category, index: number) => (
        <li key={index}>
            <div className="checkbox ml-3">
                <label>
                    <input
                        id={'categoryCheckbox'+value.id}
                        onChange={() => handleToggle2(value.id)}
                        type="checkbox"
                        checked={checkedState[value.id]}/>
                    <span className="cr">
                        <i className="cr-icon fas fa-check"></i></span>
                    {value.name}
                </label>
            </div>
            {renderSubcategories(value.array)}
        </li>
    ));

    const renderSubcategories = (subcategoriesArray: SubCategory[]) => subcategoriesArray.map((value: SubCategory, index: number) => (
        <ul key={index}>
            <div className="checkbox ml-3">
                <label>
                    <input
                        id={'categoryCheckbox'+value.id}
                        onChange={() => handleToggle2(value.id)}
                        type="checkbox"
                        checked={checkedState[value.id]}/>
                    <span className="cr">
                        <i className="cr-icon fas fa-check"></i></span>
                    {value.name}
                </label>
            </div>
        </ul>
    ));

    return (
        <ul className="list-unstyled">
            {renderCheckboxLists()}
        </ul>
    );
};

export default CheckBoxWithSubcategories;
