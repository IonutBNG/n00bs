import React, { Component } from 'react';
import HeaderNavbar from './navbars/HeaderNavbar';
import { Image, Col, Row, Container } from 'react-bootstrap';
import StarRatings from 'react-star-ratings';
import '../styles/components/GamePage.scss';
import { Service } from '../service/Service';

class GamePage extends Component {

    constructor(props) {
        super(props);
        //this.addToWishlist = this.addToWishlist.bind(this)
        this.state = {
            email: '',
            user: null,
            game: this.props.location.state.game,
            isInWishlist: false
        };
    }


    onStorage = () => {
        console.log("onStorage")
        this.setState({
            email: localStorage.getItem('email')


        })
        // Receive changes in the localStorage
    }

    componentDidMount() {
        let eml = localStorage.getItem('email')
        console.log("eml: " + eml)
        this.setState({
            email: eml,
            game: this.props.location.state.game
        })
        console.log(this.state.email)

        this.getUsrAndState(eml)
    }

    getUsrAndState = (eml) => {
        Service.getUserByEmail(eml)
            .then((usr) => {
                console.log("id user: " + usr.id)
                return usr.id
            }
            ).then((id_usr) => {
                console.log(id_usr)
                Service.isInWishlist(id_usr, this.props.location.state.game.id)
                    .then(() => {
                        console.log("yes")
                        this.setState({
                            isInWishlist: true
                        })
                    }
                    )
                    .catch((err) => console.log(err))
            })
            .catch((err) => console.log(err))
    }

    addToWishlist = () => {
        console.log("addToWishlist")
        let mail = this.state.email
        console.log(mail)
        Service.getUserByEmail(mail)
            .then((usr) => {
                console.log(usr)
                return usr.id
            }
            ).then((id_usr) => {
                console.log(id_usr)
                Service.addToWishlist(id_usr, this.state.game.id)
                    .then(() => {
                        console.log("yes")
                        this.setState({
                            isInWishlist: true
                        })
                    }
                    )
                    .catch((err) => console.log(err))
            })
            .catch((err) => console.log(err))
    }

    removeFromWishlist = () => {
        console.log("removeFromWishlist")
        let mail = this.state.email
        console.log(mail)
        Service.getUserByEmail(mail)
            .then((usr) => {
                console.log(usr)
                return usr.id
            }
            ).then((id_usr) => {
                console.log(id_usr)
                Service.removeFromWishlist(id_usr, this.state.game.id)
                    .then(() => {
                        console.log("yes")
                        this.setState({
                            isInWishlist: false
                        })
                    }
                    )
                    .catch((err) => console.log(err))
            })
            .catch((err) => console.log(err))
    }

    render() {
        const game = this.props.location.state.game;
        return (
            <div className="Full-view">
                <Container>
                    <Row noGutters>
                        <HeaderNavbar />
                    </Row>
                    <Container>
                        <Row className="Game-div justify-content-center" noGutters>
                            <Col md={4}>
                                <Image className="Game-image" src={game.cover_url} alt="Game Image"></Image>
                            </Col>
                            <Col md={8} className="game-page-content">
                                <h1 className="game-page-title">{game.name}
                                    {this.state.email &&
                                        <a onClick={this.state.isInWishlist ? this.removeFromWishlist : this.addToWishlist} title={this.state.isInWishlist ? "remove from wishlist" : "add to wishlist"}>
                                            <Image className={this.state.isInWishlist ? 'isInWishlist-img-style' : 'addWishlist-img-style'}
                                                alt='addToWishlist'
                                                src={require('../images/stars.png')}></Image>
                                        </a>
                                    }
                                </h1>
                                <h6>{game.release_date}</h6>
                                <StarRatings
                                    rating={4.4}
                                    starRatedColor="#F86210"
                                    changeRating={this.changeRating}
                                    numberOfStars={5}
                                    name='rating'
                                    starDimension="15px"
                                    starSpacing="2px"
                                />
                                <br />
                                <span>Genre: </span>
                                <br />
                                <span>Platforms: </span>
                                <br />
                                <br />
                                <p>{game.summary}</p>
                            </Col>
                        </Row>
                        <Row className='more-info-div' noGutters>

                        </Row>
                        <Row className='review-div' noGutters>
                            {/* read and write reviews */}
                        </Row>
                        <Row className='reccomending-div' noGutters>

                        </Row>
                    </Container>
                </Container>
            </div>
        )
    }
}

export default GamePage;