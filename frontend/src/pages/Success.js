import {useLocation} from 'react-router-dom';
import React, { useState } from "react";
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import {Text} from 'react-native';
import Col from "react-bootstrap/Col";

function Shipment() {

    const location = useLocation();

    const handleClick = () => {
        window.open(location.state.labelUrl, "_blank");
    }

    return (
        <Container>
            <br/>
            <br/>
            <br/>
            <br/>
            <Row className="justify-content-md-center">
                <Col xs lg="3">
                    <Text style={{fontSize: 20}}>Success</Text>
                </Col>
            </Row>
            <br/>
            <Row className="justify-content-md-center">
                <Col xs lg="3"><label style={{fontSize: 18}}>ShipId: <Text numberOfLines={5}>{location.state.shipId}</Text></label></Col>
            </Row>
            <Row className="justify-content-md-center">
                <Col xs lg="3"><label style={{fontSize: 18}}>Tracking: <Text numberOfLines={5}>{location.state.trackingNumbers}</Text></label></Col>
            </Row>
            <Row className="justify-content-md-center">
                <Col xs lg="3"><button onClick={handleClick}>Download PDF Label</button></Col>
            </Row>

        </Container>
    );
}

export default Shipment;



