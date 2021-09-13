import React, {FC} from 'react';
import {faInfoCircle} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const Contacts: FC = () => {
    return (
        <div className="container mt-5">
            <h4><FontAwesomeIcon className="ml-2 mr-2" icon={faInfoCircle}/>Contacts</h4>
            <br/>
            <p>
                <b>E-mail:</b> test@gmail.com</p>
            <br/>
            <br/>
            <h6>Delivery</h6>
            <p>Delivery of orders come through courier service.</p>
        </div>
    );
};

export default Contacts
