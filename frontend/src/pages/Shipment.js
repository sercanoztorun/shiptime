import {useLocation} from 'react-router-dom';
import React, { useState } from "react";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import {useNavigate} from "react-router";

function Shipment() {

    const location = useLocation();
    const [shipmentValue, setShipmentValue] = useState(null);

    function onChangeValue(event) {
        setShipmentValue(event.target.value);
    }
    let navigate = useNavigate();

    const handleClick = () => {
        if(shipmentValue == null){
            alert("Please choose any shipment option to continue");
        }
        const data = {
            id : shipmentValue
        }
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        };
        fetch('http://localhost:8081/shipments', requestOptions)
            .then(response => {
                if(response.ok){
                    return response.json()
                }
                return response.text().then(text => { throw new Error(text) })
            })
            .then((data) => {
                navigate('/success',{state:data});
                console.log(data);
            })
            .catch((error) => {
                alert(error);
            });

    };

    return (
        <Container>
            <Row className="justify-content-md-center">
                <Col xs lg="2">Carrier</Col>
                <Col xs lg="2">Service</Col>
                <Col xs lg="2">Price</Col>
            </Row>
        <div onChange={onChangeValue}>
            {location.state.map((shipment) => (
                <Row className="justify-content-md-center">
                    <Col xs lg="2"><div><input type="radio" value={shipment.id} name="gender" checked={shipmentValue === shipment.id}/> &nbsp;{shipment.carrierName}</div></Col>
                    <Col xs lg="2">{shipment.serviceName}</Col>
                    <Col xs lg="2">{shipment.price}-{shipment.currency}</Col>
                </Row>
            ))}

        </div>
            <br/>
            <Row className="justify-content-md-center">
                <button onClick={handleClick}>Confirm Shipping</button>
            </Row>
        </Container>
    );
}

export default Shipment;



