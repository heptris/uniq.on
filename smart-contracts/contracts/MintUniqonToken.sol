// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "./token/ERC721/extensions/ERC721Enumerable.sol";
import "./token/ERC20/ERC20.sol";

/**
 * PJT Ⅰ - 과제 2) NFT Creator 구현
 * 상태 변수나 함수의 시그니처는 구현에 따라 변경할 수 있습니다.
 */
contract MintUniqonToken is ERC721Enumerable{

    uint256 private _tokenIds;
    mapping(uint256 => string) tokenURIs;
    mapping(uint256 => uint256) public uniqonTokenPrices;
    uint256[] public onSaleUniqonToken;
    IERC20 public erc20Contract;
    address payable public startupAddress;
    
    event showTokenURI(string indexed tokenURI);

    constructor() ERC721("Uniq.on-NFT", "UNFT") {
        erc20Contract = IERC20(0x0c54E456CE9E4501D2c43C38796ce3F06846C966);
    }

    function setStartupAddress(address payable _startupAddress) public {
        startupAddress = _startupAddress;
    }

    function current() public view returns (uint256) {
        return _tokenIds;
    }

    function tokenURI(uint256 tokenId) public view override returns (string memory) {
        return tokenURIs[tokenId];
    }

    // function setApprovalForAllSale(address saleFactolyAddress) public {
    //     ERC721.setApprovalForAll(saleFactolyAddress, true);
    // }

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

    function create(address to, string memory _tokenURI, uint repeat, uint price) public returns (uint256) {
        // uint256 tokenId = current() + 1;
        // tokenURIs[tokenId] = _tokenURI;
        // _tokenIds = tokenId;
        // _tokenIds = totalSupply() + 1;

        setApprovalForAll(address(this), true);

        for(uint i=0; i<repeat; i++){
            tokenURIs[_tokenIds+1] = _tokenURI;
            _tokenIds += 1;
            _mint(to, _tokenIds);
            setForSaleUniqonToken(_tokenIds, price);
        }


        // emit createNFT(_tokenIds+1, to); // block에 저장 + print 기능
        return _tokenIds;
    }

    function getOwnedTokens(address owner) public view returns (string[] memory) {
        uint256 tokenCount = balanceOf(owner);

        uint256[] memory tokens = new uint[](tokenCount);
        string[] memory tokensURI = new string[](tokenCount);
        for(uint i=0; i<tokenCount; i++){
            tokens[i] = (tokenOfOwnerByIndex(owner, i));
            tokensURI[i] = tokenURI(tokens[i]);
        }
        return tokensURI;
    }

    function getTokenPrice(uint256 _uniqonTokenId) public view returns (uint256) {
        return uniqonTokenPrices[_uniqonTokenId];
    }

    function getOnSaleUniqonToken() public view returns (uint256[] memory) {
        return onSaleUniqonToken;
    }

    function getSSFBalance() public view returns (uint256) {
        return erc20Contract.balanceOf(msg.sender);
    }

    function transferSSF(address payable _startupAddress) public payable {
        address sender = msg.sender;
        uint price = 3;
        require(erc20Contract.approve(_startupAddress, price), "address fail");
        require(erc20Contract.approve(sender, price), "msg.sender fail");
        require(getSSFBalance() > 0, "caller has no money");
        
        // address uniqonTokenOwner = ownerOf(1);

        // erc20Contract.transferFrom(msg.sender, address(this), 1);
        erc20Contract.transfer(_startupAddress, 1);
    }

    function transferSSF2() public payable {
        address sender = msg.sender;
        uint price = 3;
        require(erc20Contract.approve(address(this), price), "address fail");
        require(erc20Contract.approve(sender, price), "msg.sender fail");
        require(getSSFBalance() > 0, "caller has no money");
        // 보내는 사람 != 받는사람
        // 처음에 msg.sender 저장해놔야함
        
        // address uniqonTokenOwner = ownerOf(1);

        erc20Contract.transferFrom(sender, address(this), 1);
        // erc20Contract.transfer(startupAddress, 1);
    }

    function purchaseUniqonToken(uint256 _uniqonTokenId) public payable {
        uint256 price = uniqonTokenPrices[_uniqonTokenId];
        address uniqonTokenOwner = ownerOf(_uniqonTokenId);

        require(price > 0, "This uniqon token not sale.");
        require(price <= msg.value, "Caller sent lower than price.");
        require(
            uniqonTokenOwner != msg.sender,
            "Caller is uniqon token owner."
        );

        // payable(uniqonTokenOwner).transfer(msg.value);
        erc20Contract.transferFrom(msg.sender, uniqonTokenOwner, price);
        IERC721(address(this)).safeTransferFrom(uniqonTokenOwner, msg.sender, _uniqonTokenId);

        uniqonTokenPrices[_uniqonTokenId] = 0;

        for (uint256 i = 0; i < onSaleUniqonToken.length; i++) {
            if (uniqonTokenPrices[onSaleUniqonToken[i]] == 0) {
                onSaleUniqonToken[i] = onSaleUniqonToken[
                    onSaleUniqonToken.length - 1
                ];
                onSaleUniqonToken.pop();
            }
        }
        
        emit showTokenURI(tokenURIs[_uniqonTokenId]); // block에 저장 + print 기능
    }
}