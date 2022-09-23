// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "./token/ERC721/extensions/ERC721Enumerable.sol";

/**
 * PJT Ⅰ - 과제 2) NFT Creator 구현
 * 상태 변수나 함수의 시그니처는 구현에 따라 변경할 수 있습니다.
 */
contract MintUniqonToken is ERC721Enumerable{

    uint256 private _tokenIds;
    mapping(uint256 => string) tokenURIs;

    event createNFT(uint256 indexed _tokenId, address indexed _owner);

    constructor() ERC721("Uniq.on-NFT", "UNFT") {
        _tokenIds = 0;
        // tokenURIs[0] = "123123";
    }

    function current() public view returns (uint256) {
        return _tokenIds;
    }

    function tokenURI(uint256 tokenId) public view override returns (string memory) {
        return tokenURIs[tokenId];
    }

    function create(address to, string memory _tokenURI) public returns (uint256) {
        // uint256 tokenId = current() + 1;
        // tokenURIs[tokenId] = _tokenURI;
        // _tokenIds = tokenId;
        // _tokenIds = totalSupply() + 1;
        tokenURIs[_tokenIds+1] = _tokenURI;
        _tokenIds += 1;
        _mint(to, _tokenIds);
        // emit createNFT(_tokenIds+1, to); // block에 저장 + print 기능
        return _tokenIds;
    }
}