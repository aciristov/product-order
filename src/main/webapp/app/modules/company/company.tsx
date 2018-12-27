import React from 'react';
import axios from 'axios';

import {Input, FormGroup, Label, Modal, ModalHeader, ModalBody, ModalFooter, Table, Button} from 'reactstrap';
import {Storage} from "react-jhipster";

class CompanyPage extends React.Component {

  state = {
    companies: [],
    newCompanyData: {
      name: '',
      city: '',
      phone: '',
      address: ''
    },
    editCompanyData: {
      id: '',
      name: '',
      city: '',
      phone: '',
      address: ''
    },
    newCompanyModal: false,
    editCompanyModal: false
  };


  componentWillMount() {

    const token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');

    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;

    axios.get('http://localhost:8080/companyAPI/companies')
      .then(res => {
        const companies = res.data.content;
        this.setState({companies});
      });

    this._refreshCompanies();

  }

  toggleNewCompanyModal() {
    this.setState({
      newCompanyModal: ! this.state.newCompanyModal
    });
  }

  toggleEditCompanyModal() {
    this.setState({
      editCompanyModal: ! this.state.editCompanyModal
    });
  }

  addCompany(){
    axios.post('http://localhost:8080/companyAPI/companies', this.state.newCompanyData).then(response => {
      let { companies } = this.state;
      console.log("RESPONSE : ", response);
      console.log(response.data);
      console.log(response.data.content);
      companies.push(response.data);


      // RESET THE NEW COMPANY DATA, "newCompanyData"
      this.setState({ companies, newCompanyModal: false, newCompanyData: {
          name: '',
          city: '',
          phone: '',
          address: ''
        }});

      console.log(response.data.content);
    });
  }

  updateCompany(){
    let {name, city, phone, address} = this.state.editCompanyData;
    axios.put(`http://localhost:8080/companyAPI/companies/${this.state.editCompanyData.id}`, {
      name, city, phone, address
    }).then((response) => {
      let { companies } = this.state;
      console.log(response);
      const updatedCompany = response.data;

      const updatedCompanies = companies.map(company => {
        if (company.id !== updatedCompany.id) {
          return company;
        }
        return updatedCompany;
      });
      this.setState({ ...this.state,  companies: updatedCompanies});
      this.toggleEditCompanyModal();
    });
  }

  editCompany(id, name, city, phone, address){
    this.setState({
      editCompanyData: { id, name, city, phone, address}, editCompanyModal: ! this.state.editCompanyModal
    });

  }

  deleteCompany(id){
    axios.delete('http://localhost:8080/companyAPI/companies/'+ id).then(response =>{
      let { companies } = this.state;

      const filteredCompanies = companies.filter(company => company.id !== id);
      this.setState({ ...this.state,  companies: filteredCompanies});
    });
  }

  _refreshCompanies(){
    axios.get('http://localhost:8080/companyAPI/companies')
      .then(res => {
        const companies = res.data.content;
        this.setState({companies});
      })
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
            <Button color="success" size="sm" className="mr-2" onClick={this.editCompany.bind(this, company.id, company.name, company.city, company.phone, company.address)} >Edit</Button>
            <Button color="danger" size="sm" onClick={this.deleteCompany.bind(this, company.id)} >Delete</Button>
          </td>
        </tr>
      )
    });

    return (
      <div className="App container">

        <h2> Companies App </h2>
        <br/><Button className="my-1" color="primary" onClick={this.toggleNewCompanyModal.bind(this)}>Add Company</Button><br/><br/>

  {/*MODAL FOR POST MODAL, CREATE COMPANY!!!*/}

                    <Modal isOpen={this.state.newCompanyModal} toggle={this.toggleNewCompanyModal.bind(this)}>
                      <ModalHeader toggle={this.toggleNewCompanyModal.bind(this)}>Add a new company</ModalHeader>

                        <ModalBody>
                          <FormGroup>
                            <Label for="name"> Name </Label>
                            <Input id="name" value={this.state.newCompanyData.name} onChange={(e) => {
                              let { newCompanyData } = this.state;

                              newCompanyData.name = e.target.value;

                              this.setState( { newCompanyData });
                            }} />
                          </FormGroup>

                          <FormGroup>
                            <Label for="city"> City </Label>
                            <Input id="city" value={this.state.newCompanyData.city} onChange={(e) => {

                              let { newCompanyData } = this.state;

                              newCompanyData.city = e.target.value;

                              this.setState( { newCompanyData });

                            }} />
                          </FormGroup>

                          <FormGroup>
                            <Label for="phone"> Phone </Label>
                            <Input id="phone" value={this.state.newCompanyData.phone} onChange={(e) => {
                              let { newCompanyData } = this.state;

                              newCompanyData.phone = e.target.value;

                              this.setState({ newCompanyData });
                            }}/>
                          </FormGroup>

                          <FormGroup>
                            <Label for="address"> Address </Label>
                            <Input id="address" value={this.state.newCompanyData.address} onChange={(e) => {

                              let { newCompanyData } = this.state;

                              newCompanyData.address = e.target.value;

                              this.setState({ newCompanyData });


                            }} />


                          </FormGroup>

                        </ModalBody>

                      <ModalFooter>

                        <Button color="primary" onClick={this.addCompany.bind(this)}>Add Company</Button>{' '}
                        <Button color="secondary" onClick={this.toggleNewCompanyModal.bind(this)}>Cancel</Button>
                      </ModalFooter>
                    </Modal>

  {/*END MODAL FOR POST!!! */}

  {/*START MODAL FOR EDIT!!!*/}

                    <Modal isOpen={this.state.editCompanyModal} toggle={this.toggleEditCompanyModal.bind(this)}>
                      <ModalHeader toggle={this.toggleEditCompanyModal.bind(this)}>Edit an company</ModalHeader>

                      <ModalBody>
                        <FormGroup>
                          <Label for="name"> Name </Label>
                          <Input id="name" value={this.state.editCompanyData.name} onChange={(e) => {
                            let { editCompanyData } = this.state;

                            editCompanyData.name = e.target.value;

                            this.setState( { editCompanyData });
                          }} />
                        </FormGroup>

                        <FormGroup>
                          <Label for="city"> City </Label>
                          <Input id="city" value={this.state.editCompanyData.city} onChange={(e) => {

                            let { editCompanyData } = this.state;

                            editCompanyData.city = e.target.value;

                            this.setState( { editCompanyData });

                          }} />
                        </FormGroup>

                        <FormGroup>
                          <Label for="phone"> Phone </Label>
                          <Input id="phone" value={this.state.editCompanyData.phone} onChange={(e) => {
                            let { editCompanyData } = this.state;

                            editCompanyData.phone = e.target.value;

                            this.setState({ editCompanyData });
                          }}/>
                        </FormGroup>

                        <FormGroup>
                          <Label for="address"> Address </Label>
                          <Input id="address" value={this.state.editCompanyData.address} onChange={(e) => {

                            let { editCompanyData } = this.state;

                            editCompanyData.address = e.target.value;

                            this.setState({ editCompanyData });


                          }} />


                        </FormGroup>

                      </ModalBody>

                      <ModalFooter>

                        <Button color="primary" onClick={this.updateCompany.bind(this)}>Update Company</Button>{' '}
                        <Button color="secondary" onClick={this.toggleEditCompanyModal.bind(this)}>Cancel</Button>
                      </ModalFooter>
                    </Modal>


  {/*END MODAL FOR EDIT!!!*/}


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

          {/*MAP ALL THE COMPANIES WITH TD*/}
          { companies }

          </tbody>

        </Table>


      </div>
    );
  }
}

export default CompanyPage;
