// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "./token/ERC721/extensions/ERC721Enumerable.sol";
import "./token/ERC20/ERC20.sol";
import "./SSF.sol";

/**
 * PJT Ⅰ - 과제 2) NFT Creator 구현
 * 상태 변수나 함수의 시그니처는 구현에 따라 변경할 수 있습니다.
 */
contract MintUniqonToken is ERC721Enumerable{

    uint256 private _tokenIds;
    mapping(uint256 => string) tokenURIs;
    mapping(uint256 => uint256) public uniqonTokenPrices;
    uint256[] public onSaleUniqonToken;
    SSF public erc20Contract;
    
    event lastTokenId(uint indexed lastTokenId);
    event showTokenURI(string[] indexed tokenURI);

    constructor() ERC721("Uniq.on-NFT", "UNFT") {
        erc20Contract = SSF(0xB39D41Fe05d0cC6271513A47C89aC829f88952bF);
    }

    function current() public view returns (uint256) {
        return _tokenIds;
    }

    function tokenURI(uint256 tokenId) public view override returns (string memory) {
        return tokenURIs[tokenId];
    }

    function setForSaleUniqonToken(uint256 _uniqonTokenId, uint256 _price)
        public
    {
        address uniqonTokenOwner = ownerOf(_uniqonTokenId);
        require(
            uniqonTokenOwner == msg.sender,
            "Caller is not uniqonToken owner."
        );
        require(_price > 0, "Price is zero is lower.");
        require(
            uniqonTokenPrices[_uniqonTokenId] == 0,
            "This uniqon token is already on sale."
        );
        require(
            isApprovedForAll(uniqonTokenOwner, address(this)),
            "Uniqon token owner did not approve token."
        );

        // 가격을 prices에 매핑
        uniqonTokenPrices[_uniqonTokenId] = _price;

        // 토큰 아이디로 판매중 토큰 배열에 저장
        onSaleUniqonToken.push(_uniqonTokenId);
    }

    // NFT Minting
    function create(address to, string memory _tokenURI, uint repeat, uint price) public returns (uint256) {

        // 현재 contract에 모든 NFT 전송 권한 넘기기
        setApprovalForAll(address(this), true);

        // 발행 개수 만큼 Minting
        for(uint i=0; i<repeat; i++){
            tokenURIs[_tokenIds+1] = _tokenURI;
            _tokenIds += 1;
            _mint(to, _tokenIds);
            setForSaleUniqonToken(_tokenIds, price);
        }

        emit lastTokenId(_tokenIds);
        return _tokenIds;
    }

    function getOwnedTokens(address owner) public returns (string[] memory) {
        uint256 tokenCount = balanceOf(owner);

        uint256[] memory tokens = new uint[](tokenCount);
        string[] memory tokensURI = new string[](tokenCount);
        for(uint i=0; i<tokenCount; i++){
            tokens[i] = (tokenOfOwnerByIndex(owner, i));
            tokensURI[i] = tokenURI(tokens[i]);
        }
        emit showTokenURI(tokensURI); // block에 저장 + print 기능
        return tokensURI;
    }

    function getTokenPrice(uint256 _uniqonTokenId) public view returns (uint256) {
        return uniqonTokenPrices[_uniqonTokenId];
    }

    function getOnSaleUniqonToken() public view returns (uint256[] memory) {
        return onSaleUniqonToken;
    }

    function getUniqonTBalance() public view returns (uint256) {
        return erc20Contract.balanceOf(msg.sender);
    }
    
    function getUniqonTtotalSupply() public view returns (uint256) {
        return erc20Contract.totalSupply();
    }

    // NFT 구매
    function purchaseUniqonToken(uint256 _uniqonTokenId) public {
        address buyer = msg.sender;
        uint256 price = uniqonTokenPrices[_uniqonTokenId];
        address uniqonTokenOwner = ownerOf(_uniqonTokenId);

        require(price > 0, "This uniqon token not sale.");
        require(
            uniqonTokenOwner != msg.sender,
            "Caller is uniqon token owner."
        );


        require(erc20Contract.approve(address(this), erc20Contract.balanceOf(buyer)), "address fail");
        require(erc20Contract.approve(buyer, price), "msg.sender fail");
        require(erc20Contract.allowance(buyer, address(this)) != 0, "buyer did not approve this contract");
        require(erc20Contract.allowance(buyer, address(this)) >= price, "caller approve less amount of token");
        require(getUniqonTBalance() > 0, "caller has no money");
        erc20Contract.transferFrom(buyer, uniqonTokenOwner, price);
        IERC721(address(this)).safeTransferFrom(uniqonTokenOwner, buyer, _uniqonTokenId);

        uniqonTokenPrices[_uniqonTokenId] = 0;

        for (uint256 i = 0; i < onSaleUniqonToken.length; i++) {
            if (uniqonTokenPrices[onSaleUniqonToken[i]] == 0) {
                onSaleUniqonToken[i] = onSaleUniqonToken[
                    onSaleUniqonToken.length - 1
                ];
                onSaleUniqonToken.pop();
            }
        }
        
    }
}