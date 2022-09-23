// SPDX-License-Identifier: MIT

pragma solidity ^0.8.0;

import "./MintUniqonToken.sol";

contract SaleFactory {
    MintUniqonToken public mintUniqonTokenAddress;

    constructor(address _mintUniqonTokenAddress) {
        mintUniqonTokenAddress = MintUniqonToken(_mintUniqonTokenAddress);
    }

    mapping(uint256 => uint256) public uniqonTokenPrices;

    uint256[] public onSaleUniqonToken;

    function setForSaleUniqonToken(uint256 _uniqonTokenId, uint256 _price)
        public
    {
        address uniqonTokenOwner = mintUniqonTokenAddress.ownerOf(
            _uniqonTokenId
        );

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
            mintUniqonTokenAddress.isApprovedForAll(uniqonTokenOwner, address(this)),
            "Uniqon token owner did not approve token."
        );

        // 가격을 prices에 매핑
        uniqonTokenPrices[_uniqonTokenId] = _price;

        // 토큰 아이디로 판매중 토큰 배열에 저장
        onSaleUniqonToken.push(_uniqonTokenId);
    }

    function purchaseUniqonToken(uint256 _uniqonTokenId) public payable {
        uint256 price = uniqonTokenPrices[_uniqonTokenId];
        address uniqonTokenOwner = mintUniqonTokenAddress.ownerOf(_uniqonTokenId);

        require(price > 0, "This uniqon token not sale.");
        require(price <= msg.value, "Caller sent lower than price.");
        require(
            uniqonTokenOwner != msg.sender,
            "Caller is uniqon token owner."
        );

        payable(uniqonTokenOwner).transfer(msg.value);
        mintUniqonTokenAddress.safeTransferFrom(
            uniqonTokenOwner,
            msg.sender,
            _uniqonTokenId
        );

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

    function getTokenPrice(uint256 _uniqonTokenId)
        public
        view
        returns (uint256)
    {
        return uniqonTokenPrices[_uniqonTokenId];
    }

    function getOnSaleUniqonToken() public view returns (uint256[] memory) {
        return onSaleUniqonToken;
    }
}