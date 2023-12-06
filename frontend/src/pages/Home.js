import React, { useState } from 'react';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import { useNavigate } from "react-router";

function Home() {
    const [rateData, setRateData] = useState({});
    const handleRateChange = (event) => {
        setRateData({
            ...rateData,
            [event.target.name]: event.target.value
        })
    };

    const handleClick = () => {
        const from = {
            companyName: rateData.inputOriginCompanyName,
            streetAddress: rateData.inputOriginAddressLine,
            city: rateData.inputOriginCity,
            countryCode: rateData.inputOriginCountry,
            state: rateData.inputOriginProvince,
            postalCode: rateData.inputOriginPostalCode,
            attention: rateData.inputOriginAttention,
            phone: rateData.inputOriginPhoneNumber
        }
        const to = {
            companyName: rateData.inputDestinationCompanyName,
            streetAddress: rateData.inputDestinationAddressLine,
            city: rateData.inputDestinationCity,
            countryCode: rateData.inputDestinationCountry,
            state: rateData.inputDestinationProvince,
            postalCode: rateData.inputDestinationPostalCode,
            attention: rateData.inputDestinationAttention,
            phone: rateData.inputDestinationPhoneNumber
        }
        const lineItem = {
            length : rateData.inputLength,
            width : rateData.inputWidth,
            height : rateData.inputHeight,
            weight : rateData.inputWeight
        }
        const data = {from, to, lineItem};
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        };
        fetch('http://localhost:8081/rates', requestOptions)
            .then(response => {
                if(response.ok){
                    return response.json()
                }
                return response.text().then(text => { throw new Error(text) })
            })
            .then((data) => {
                navigate('/shipment',{state:data});
                console.log(data);
            })
            .catch((error) => {
                alert(error);
            });

    };
    let navigate = useNavigate();

    return (
        <Container>
            <br/>
            <br/>
            <Row>
                <Col>Origin Address</Col>
                <Col>Destination Address</Col>
            </Row>
            <Row>
                <Col>
                    <label>
                        Address Line 1: <input name="inputOriginAddressLine" onChange={handleRateChange} />
                    </label>
                    <label>
                        City: <br/><input name="inputOriginCity" onChange={handleRateChange}/>
                    </label>
                    <label>
                        Province: <br/>
                        <select name="inputOriginProvince" onChange={handleRateChange}>
                            <option value=''></option>
                            <option value="ON">Ontario</option>
                            <option value="AB">Alberta</option>
                            <option value="BC">British Columbia</option>
                            <option value="NS">Nova Scotia</option>
                        </select>
                    </label>
                    <label>
                        Company Name: <br/><input name="inputOriginCompanyName" onChange={handleRateChange} />
                    </label>
                </Col>
                <Col>
                    <label>
                        PostalCode: <input name="inputOriginPostalCode" onChange={handleRateChange} />
                    </label>
                    <label>
                        Country: <br/>
                        <select name="inputOriginCountry" onChange={handleRateChange}>
                            <option value=''></option>
                            <option value="CA">Canada</option>
                            <option value="US">US</option>
                        </select>
                    </label>
                    <label>
                        Phone Number: <br/><input name="inputOriginPhoneNumber" onChange={handleRateChange}  />
                    </label>
                    <label>
                        Attention: <br/><input name="inputOriginAttention" onChange={handleRateChange}  />
                    </label>

                </Col>
                <Col>
                    <label>
                        Address Line 1: <input name="inputDestinationAddressLine" onChange={handleRateChange}  />
                    </label>
                    <label>
                        City: <br/><input name="inputDestinationCity" onChange={handleRateChange}  />
                    </label>
                    <label>
                        Province: <br/>
                        <select name="inputDestinationProvince" onChange={handleRateChange}>
                            <option value=''></option>
                            <option value="ON">Ontario</option>
                            <option value="AB">Alberta</option>
                            <option value="BC">British Columbia</option>
                            <option value="NS">Nova Scotia</option>
                        </select>
                    </label>
                    <label>
                        Company Name: <br/><input name="inputDestinationCompanyName" onChange={handleRateChange}  />
                    </label>
                </Col>
                <Col>
                    <label>
                        PostalCode: <input name="inputDestinationPostalCode" onChange={handleRateChange}  />
                    </label>
                    <label>
                        Country: <br/>
                        <select name="inputDestinationCountry" onChange={handleRateChange}>
                            <option value=''></option>
                            <option value="CA">Canada</option>
                            <option value="US">US</option>
                        </select>
                    </label>
                    <label>
                        Phone Number: <br/><input name="inputDestinationPhoneNumber" onChange={handleRateChange} />
                    </label>
                    <label>
                        Attention: <br/><input name="inputDestinationAttention" onChange={handleRateChange} />
                    </label>
                </Col>
            </Row>
            <br/>
            Package Information
            <br/>
            <Row>
                <Col>
                    <label>
                        Length: <br/><input name="inputLength" onChange={handleRateChange} />
                    </label>
                </Col>
                <Col>
                    <label>
                        Width: <br/><input name="inputWidth" onChange={handleRateChange} />
                    </label>
                </Col>
                <Col>
                    <label>
                        Height: <br/><input name="inputHeight" onChange={handleRateChange} />
                    </label>
                </Col>
                <Col>
                    <label>
                        Weight: <br/><input name="inputWeight" onChange={handleRateChange} />
                    </label>
                </Col>
            </Row>
            <br/>
            <br/>
            <br/>
            <Row>
                <button onClick={handleClick}>Get Rates</button>
            </Row>

        </Container>

    );
}

export default Home;
