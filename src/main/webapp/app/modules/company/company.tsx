import React from 'react';
import axios from 'axios';

import { Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button } from 'reactstrap';
import {Storage} from "react-jhipster";

class CompanyPage extends React.Component {

    state = {
        companies: [],
        newCompanyData: {
            name : '',
            city: '',
            phone: '',
            address: ''
        },
        newCompanyModal: false
    };



    componentWillMount() {

      const token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');

      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

        axios.get('http://localhost:8080/companyAPI/companies')
            .then(res => {
                const companies = res.data.content;
                this.setState({ companies });
            });
    }

    toggleNewCompanyModal() {
        this.setState({
            newCompanyModal: true
        });
    }

    render() {

        let companies = this.state.companies.map(company => {
            return (

                <tr key={company.id}>
                    <td>{company.id}</td>
                    <td>{company.name}</td>

                    <td>{company.city}</td>
                    <td>{company.phone}</td>
                    <td>{company.address}</td>

                    <td>
                        <Button color="success" size="sm" className="mr-2">Edit</Button>
                        <Button color="danger" size="sm">Delete</Button>
                    </td>
                </tr>
            )
        });

        return (
            <div className="App container">

                {/*MODAL FOR INPUTS!!!*/}

                <br/><Button color="primary" onClick={this.toggleNewCompanyModal.bind(this)}>Add Company</Button><br/><br/>

                <Modal isOpen={this.state.newCompanyModal} toggle={this.toggleNewCompanyModal.bind(this)}>
                    <ModalHeader toggle={this.toggleNewCompanyModal.bind(this)}>Add a new company</ModalHeader>
                    <ModalBody>
                        <FormGroup>
                            <Label for="name"> Name </Label>
                            <Input id="name" />

                            <Label for="city"> City </Label>
                            <Input id="city" />

                            <Label for="phone"> Phone </Label>
                            <Input id="phone" />

                            <Label for="address"> Address </Label>
                            <Input id="address" />
                        </FormGroup>
                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={this.toggleNewCompanyModal.bind(this)}>Add Company</Button>{' '}
                        <Button color="secondary" onClick={this.toggleNewCompanyModal.bind(this)}>Cancel</Button>
                    </ModalFooter>
                </Modal>

                <Table>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>City</th>
                        <th>Phone</th>
                        <th>Address</th>
                    </tr>
                    </thead>

                    <tbody>

                    { companies }
                    </tbody>

                </Table>



            </div>
        );
    }
}

export default CompanyPage;
