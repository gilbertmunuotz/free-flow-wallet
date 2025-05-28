import { DialogFooter, DialogHeader, DialogTitle } from "@/components/ui/dialog"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue, } from "@/components/ui/select"
import { Label } from "./ui/label"
import { Input } from "./ui/input"
import { Button } from "./ui/button"


export default function FundsModal() {
    return (
        <DialogHeader>
            <DialogTitle className="text-center">New Fund</DialogTitle>
            <form>
                <div className="flex flex-col gap-6">
                    <Select name="providerName" required>
                        <SelectTrigger className="w-full">
                            <SelectValue placeholder="Choose Merchant" />
                        </SelectTrigger>
                        <SelectContent>
                            <SelectItem value="crdb">CRDB</SelectItem>
                            <SelectItem value="nmb">NMB</SelectItem>
                            <SelectItem value="airtel money">Airtel Money</SelectItem>
                        </SelectContent>
                    </Select>

                    <div className="grid gap-3">
                        <Label htmlFor="amount">Amount</Label>
                        <Input
                            name="amount"
                            type="text"
                            placeholder="20"
                            required
                        />
                    </div>
                    <DialogFooter>
                        <Button type="submit">Save</Button>
                    </DialogFooter>
                </div>
            </form>
        </DialogHeader>
    )
}
